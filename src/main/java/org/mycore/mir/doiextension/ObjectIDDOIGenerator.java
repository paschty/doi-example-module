package org.mycore.mir.doiextension;

import java.util.Optional;

import org.mycore.common.config.MCRConfiguration;
import org.mycore.datamodel.metadata.MCRObjectID;
import org.mycore.pi.MCRPersistentIdentifier;
import org.mycore.pi.MCRPersistentIdentifierGenerator;
import org.mycore.pi.doi.MCRDOIParser;
import org.mycore.pi.doi.MCRDigitalObjectIdentifier;
import org.mycore.pi.exceptions.MCRPersistentIdentifierException;

/**
 * This DOI generator just use the prefix you defined in mycore.properties and use the mycore-object-id as suffix.<br/>
 * Other notable examples {@link org.mycore.pi.doi.MCRUUIDDOIGenerator} and {@link org.mycore.pi.doi.MCRCreateDateDOIGenerator}
 */
public class ObjectIDDOIGenerator implements MCRPersistentIdentifierGenerator<MCRDigitalObjectIdentifier> {

    public static final MCRDOIParser DOI_PARSER = new MCRDOIParser();

    @Override public MCRDigitalObjectIdentifier generate(MCRObjectID mcrID, String additional)
        throws MCRPersistentIdentifierException {
        String prefix = MCRConfiguration.instance().getString("MCR.DOI.Prefix");
        String identifierAsString = prefix + "/" + mcrID.toString();

        Optional<MCRPersistentIdentifier> doi = DOI_PARSER.parse(identifierAsString);
        return (MCRDigitalObjectIdentifier) doi.get();
    }
}
